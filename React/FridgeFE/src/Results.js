import React from 'react';
import { Item, Message, Header } from 'semantic-ui-react';
import './CSS/Results.css';
import loader from './Assets/loader.gif';

class Results extends React.Component {
    constructor(props) {
        super(props);
        const { ingredients } = this.props;
        this.state = {
            ingredients,
            response: [],
        };
    }

    componentWillReceiveProps(props) {
        const { ingredients } = props;
        this.setState({ ingredients });
      }

    componentDidUpdate(prevProps, prevState) {
        if (prevState.ingredients!== this.state.ingredients) {
            this.callApi()
            .then(res => this.setState({ response: res }))
            .catch(err => console.log(err));
        }
    }

    callApi = async () => {
        const ingredients = this.state.ingredients
        const ingredientsString = ingredients.join(',')
        const query = 'http://localhost:9000/?query='.concat(ingredientsString)

        this.setState({ response: [] })
        
        const response = await fetch(query);
        const body = await response.json();
        if (response.status !== 200) throw Error(body.message);
        
        return body;
      };

    render() {
       const {ingredients, response} = this.state
       
       if (response.length !== 0) {
           if (response['Recipes'].length !== 0) {
               return (
                <div>
                <Header as='h3' textAlign='center'>이건 어때?</Header>
               <Item.Group id='resultsList'>
                   {response['Recipes'].map(recipe => (
                   <Item id='recipe' href={recipe.link}>
                        <Item.Image size='tiny' src={recipe.thumbnail} />
    
                        <Item.Content>
                            <Item.Header> {recipe.title} </Item.Header>
                            <Item.Description> <p> {recipe.ingredients} </p> </Item.Description>
                        </Item.Content>
                    </Item>
                   ))}
               </Item.Group>
               </div>
               )
           } else {
               return (
                <Message color='orange'>
                <Message.Header>결과가 없네요..</Message.Header>
                <Message.List>
                    <Message.Item>재료가 부족하네요! 더 입력해주세요</Message.Item>
                    <Message.Item>아니면 마트 가셔야 될 거 같네요</Message.Item>
                    <Message.Item>그것도 아니면 입력하신 재료들을 다시 확인해주세요!</Message.Item>
                </Message.List>
              </Message>
               )
           }
       } else if (ingredients.length !== 0){
           return(
           <div><img src={loader} alt="loading..." /></div>
           )
       } else {
        return(
            <div></div>
            )
       }
    }
}


export default Results;